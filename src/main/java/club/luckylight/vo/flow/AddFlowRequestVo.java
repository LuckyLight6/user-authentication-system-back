package club.luckylight.vo.flow;

import club.luckylight.model.flow.Flow;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class AddFlowRequestVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Flow> flow;
}
