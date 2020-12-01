package dwl.service.business;

/**
 * @author wenlong.ding
 * @date 2020/11/17 11:29
 */
public interface Command<IN,OUT> {

    void exec(IN in ,OUT out);

}
