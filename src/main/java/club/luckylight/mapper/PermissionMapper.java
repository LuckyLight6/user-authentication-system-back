package club.luckylight.mapper;

import club.luckylight.dto.UseablePermissionDto;
import club.luckylight.model.Permission;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface PermissionMapper extends Mapper<Permission> {

    List<UseablePermissionDto> getRolePermissionByRoleId(Integer roleId);
}