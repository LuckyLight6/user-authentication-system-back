package club.luckylight.service.impl;

import club.luckylight.dto.UseablePermissionDto;
import club.luckylight.mapper.PermissionMapper;
import club.luckylight.mapper.UserMapper;
import club.luckylight.model.User;
import club.luckylight.model.flow.*;
import club.luckylight.service.AuthService;
import club.luckylight.util.ODLUtils;
import club.luckylight.vo.auth.LoginRequestVo;
import club.luckylight.vo.flow.AddFlowRequestVo;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;

    private final PermissionMapper permissionMapper;

    private AtomicInteger atomicFlowId = new AtomicInteger(10000);

    @Autowired
    public AuthServiceImpl(UserMapper userMapper, PermissionMapper permissionMapper) {
        this.userMapper = userMapper;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public User login(LoginRequestVo vo) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", vo.getUsername()).andEqualTo("password", vo.getPassword());
        List<User> users = userMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(users)) {
            return users.get(0);
        }

        return null;
    }

    @Override
    public User info(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", username);
        List<User> users = userMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(users)) {
            return users.get(0);
        }

        return null;
    }

    @Override
    public void openPermission(User user, String sourceIp) {
        List<UseablePermissionDto> useablePermissionList = permissionMapper.getRolePermissionByRoleId(user.getRoleId());

        for (UseablePermissionDto useablePermissionDto : useablePermissionList) {
            int flowId = atomicFlowId.getAndAdd(1);

            EthernetType ethernetType = new EthernetType();
            ethernetType.setType("2048");

            EthernetMatch ethernetMatch = new EthernetMatch();
            ethernetMatch.setEthernetType(ethernetType);

            Match match = new Match();
            match.setEthernetMatch(ethernetMatch);
            match.setIpv4Source(sourceIp + "/32");
            match.setIpv4Destination(useablePermissionDto.getPermissionIp() + "/32");

            if (ObjectUtil.isNull(useablePermissionDto.getPermissionPort())) {
                IpMatch ipMatch = new IpMatch();
                ipMatch.setIpProtocol("6");
                match.setIpMatch(ipMatch);
                match.setTcpDestinationPort(useablePermissionDto.getPermissionPort().toString());
            }

            ApplyActions applyActions = new ApplyActions();
            applyActions.setAction(new NormalAction());

            Instruction instruction = new Instruction();
            instruction.setApplyActions(applyActions);

            Instructions instructions = new Instructions();
            instructions.setInstruction(instruction);

            Flow flow = new Flow();
            flow.setFlowId(String.valueOf(flowId));
            flow.setIdleTimeout(0L);
            flow.setHardTimeout(0L);
            flow.setPriority("200");
            flow.setTableId("0");
            flow.setMatch(match);
            flow.setInstructions(instructions);

            List<Flow> flowList = new ArrayList<>();
            flowList.add(flow);

            AddFlowRequestVo addFlowRequestVo = new AddFlowRequestVo();
            addFlowRequestVo.setFlow(flowList);

            ODLUtils.addFlow("60.205.190.37", "8181", "1", flowId, JSON.toJSONString(addFlowRequestVo));
        }
    }

    @Override
    public void closePermission(User user, String sourceIp) {
        List<UseablePermissionDto> useablePermissionList = permissionMapper.getRolePermissionByRoleId(user.getRoleId());

        for (UseablePermissionDto useablePermissionDto : useablePermissionList) {
            int flowId = atomicFlowId.getAndAdd(1);

            EthernetType ethernetType = new EthernetType();
            ethernetType.setType("2048");

            EthernetMatch ethernetMatch = new EthernetMatch();
            ethernetMatch.setEthernetType(ethernetType);

            Match match = new Match();
            match.setEthernetMatch(ethernetMatch);
            match.setIpv4Source(sourceIp + "/32");
            match.setIpv4Destination(useablePermissionDto.getPermissionIp() + "/32");

            if (ObjectUtil.isNull(useablePermissionDto.getPermissionPort())) {
                IpMatch ipMatch = new IpMatch();
                ipMatch.setIpProtocol("6");
                match.setIpMatch(ipMatch);
                match.setTcpDestinationPort(useablePermissionDto.getPermissionPort().toString());
            }

            ApplyActions applyActions = new ApplyActions();
            applyActions.setAction(new DropAction());

            Instruction instruction = new Instruction();
            instruction.setApplyActions(applyActions);

            Instructions instructions = new Instructions();
            instructions.setInstruction(instruction);

            Flow flow = new Flow();
            flow.setFlowId(String.valueOf(flowId));
            flow.setIdleTimeout(0L);
            flow.setHardTimeout(0L);
            flow.setPriority("300");
            flow.setTableId("0");
            flow.setMatch(match);
            flow.setInstructions(instructions);

            List<Flow> flowList = new ArrayList<>();
            flowList.add(flow);

            AddFlowRequestVo addFlowRequestVo = new AddFlowRequestVo();
            addFlowRequestVo.setFlow(flowList);

            ODLUtils.addFlow("60.205.190.37", "8181", "1", flowId, JSON.toJSONString(addFlowRequestVo));
        }
    }

    @Override
    public List<UseablePermissionDto> getUserPermission(User user) {
        return permissionMapper.getRolePermissionByRoleId(user.getRoleId());
    }
}
