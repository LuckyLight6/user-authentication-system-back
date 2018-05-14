package club.luckylight.mapper;

import club.luckylight.model.Flow;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface FlowMapper extends Mapper<Flow> {

    @Override
    int insert(Flow flow);
}
