import java.util.HashMap;
import java.util.Map;

/**
 * 隐式上下文参数对象
 */
public class FYContext {
    //客户端本地上下文
    private static ThreadLocal<FYContext> LOCAL = new ThreadLocal<FYContext>();
    //服务端上下文
    private static ThreadLocal<FYContext> SERVER = new ThreadLocal<FYContext>();
    //隐式参数容器
    private final Map<String,String> attachment = new HashMap<String, String>();
    //获得本地上下文对象
    public static FYContext getContext(){
        FYContext fyContext = LOCAL.get();
        if (fyContext == null){
            fyContext = new FYContext();
            LOCAL.set(fyContext);
        }
        return fyContext;
    }
    //清除客户端上下文
    public void removeContext(){
        LOCAL.remove();
    }
    //清除服务器上下文
    public void removeServerContext(){
        SERVER.remove();
    }

    /**
     * 存储需要传递的参数
     * @param key
     * @param value
     */
    public void setAttachment(String key,String value){
        attachment.put(key, value);
    }

    /**
     *
     * @return 本地线程所有隐方参数容器
     */
    public Map<String,String>getAttachments(){
        return attachment;
    }

    /**
     * 获得容器参数值
     * @param key
     * @return
     */
    public String getAttachment(String key){
        return attachment.get(key);
    }

    /**
     * 接收所有的参数
     * @param attachment 参数容器
     */
    public void setAttachments(Map<String,String>attachment){
        this.attachment.putAll(attachment);
    }
}
