package dwl.service.business;

/**
 * @author wenlong.ding
 * @date 2020/11/25 15:35
 */
public interface CommandSupplier {
    Class<? extends WXCommand> getCommand();
}
