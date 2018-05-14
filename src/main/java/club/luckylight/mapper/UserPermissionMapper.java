package club.luckylight.mapper;

import club.luckylight.model.Flow;
import club.luckylight.model.UserPermission;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface UserPermissionMapper extends Mapper<UserPermission> {

    @Override
    int insert(UserPermission userPermission);
}
