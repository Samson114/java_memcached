package two;

import java.util.Date;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;


/**
* ʹ��memcached�Ļ���ʵ����.
* 
* @author ��ľ����
*
*/
public class TbeanMemcached
{
    // ����ȫ�ֵ�Ψһʵ��
    protected static MemCachedClient mcc = new MemCachedClient();
    
    protected static TbeanMemcached memCached = new TbeanMemcached();
    
    // �����뻺������������ӳ�
    static {
        // �������б����Ȩ��
        String[] servers = {"127.0.0.1:11211"};
        Integer[] weights = {3};

        // ��ȡsocke���ӳص�ʵ������
        SockIOPool pool = SockIOPool.getInstance();

        // ���÷�������Ϣ
        pool.setServers( servers );
        pool.setWeights( weights );

        // ���ó�ʼ����������С������������Լ������ʱ��
        pool.setInitConn( 5 );
        pool.setMinConn( 5 );
        pool.setMaxConn( 250 );
        pool.setMaxIdle( 1000 * 60 * 60 * 6 );

        // �������̵߳�˯��ʱ��
        pool.setMaintSleep( 30 );

        // ����TCP�Ĳ��������ӳ�ʱ��
        pool.setNagle( false );
        pool.setSocketTO( 3000 );
        pool.setSocketConnectTO( 0 );

        // ��ʼ�����ӳ�
        pool.initialize();

        // ѹ�����ã�����ָ����С����λΪK�������ݶ��ᱻѹ��
        mcc.setCompressEnable( true );
        mcc.setCompressThreshold( 64 * 1024 );
    }
    
    /**
     * �����͹��췽����������ʵ������
     *
     */
    protected TbeanMemcached()
    {
        
    }
    
    /**
     * ��ȡΨһʵ��.
     * @return
     */
    public static TbeanMemcached getInstance()
    {
        return memCached;
    }
    
    /**
     * ���һ��ָ����ֵ��������.
     * @param key
     * @param value
     * @return
     */
    public boolean add(String key, Object value)
    {
        return mcc.add(key, value);
    }
    
    public boolean add(String key, Object value, Date expiry)
    {
        return mcc.add(key, value, expiry);
    }
    
    public boolean replace(String key, Object value)
    {
        return mcc.replace(key, value);
    }
    
    public boolean replace(String key, Object value, Date expiry)
    {
        return mcc.replace(key, value, expiry);
    }
    
    /**
     * ����ָ���Ĺؼ��ֻ�ȡ����.
     * @param key
     * @return
     */
    public Object get(String key)
    {
        return mcc.get(key);
    }
    
    public static void main(String[] args)
    {
    	TbeanMemcached cache = TbeanMemcached.getInstance();
        cache.add("hello", 234);
        System.out.print("get value : " + cache.get("hello"));
        
        
        Tbean tb = new Tbean();
        tb.setName("��ľ����");
        cache.add("tb", tb);
        
        Tbean tb1 = (Tbean)cache.get("tb");
        System.out.print("name=" + tb1.getName());
        
        
        tb1.setName("��ľ����_�޸ĵ�");
        cache.add("tb1", tb1);
        tb1 = (Tbean)cache.get("tb1");
        System.out.print("name-from-tb1=" + tb1.getName());
        
    }
}