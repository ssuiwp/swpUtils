import java.util.Random;

/**
 * 跟数学有关的自制工具
 */
public class MathUtils {

    static Random r = new Random();

    /**
     * rollInt：给定最大值和最小值，返回随机整数，范围为【最小值，最大值】
     *
     * @param min 随机数最小值
     * @param max 随机数最大值
     * @return 随机int
     */
    public static int rollInt(int min, int max) {
        //最大值为负数
        if (max <= 0) {
            return (int) (-r.nextInt(max - min + 1) + max);
        }
        //最大值为正数，最小值为负数
        if (min < 0) {
            if ((double) max / (max - min) > r.nextDouble()) {
                return (int) (r.nextInt (max + 1));
            } else {
                return (int) (-r.nextInt(-min + 1));
            }
        }
        //最小值为正数
        return r.nextInt(max - min + 1) + min;
    }

    /**
     * rollDouble ：给定最大值和最小值，返回随机小数，范围为【最小值，最大值）
     *
     * @param min 随机数最小值
     * @param max 随机数最大值
     * @return 随机double
     */
    public static double rollDouble(double min, double max) {
        //最大值为负数
        if (max <= 0) {
            return (-r.nextDouble() * (max - min) + max);
        }
        //最大值为正数，最小值为负数
        if (min < 0) {
            if ((double) max / (max - min) > r.nextDouble()) {
                return (r.nextDouble() * (max));
            } else {
                return (r.nextDouble() * (min));
            }
        }
        //最小值为正数
        return r.nextDouble() * (max - min) + min;
    }

    /**
     * successRate ： 给一个事件成功概率，返回事件结果，rate范围数值范围为【0,1】
     * @param rate      事件成功概率
     * @return boolean  返回事件是否成功的结果，false代表失败，事件不发生，true代表成功，事件可以触发
     */
    public static boolean successRate(double rate) {
        return rate > r.nextDouble();
    }


    /**
     * encode
     * 拆分long为byte数组
     */
    public static byte[] encode(long num){
        byte[] b = new byte[8];
        for (byte i = 0; i < b.length; i++) {
            b[i] = (byte)((num >>> (i*8)) & 0xff);
        }
        return b;
    }

    /**
     * decode
     * 合并byte数组为long
     */
    public static long decode(byte[] bytes){
        long l = 0;
        for (int i = 0; i < bytes.length; i++) {
            l = l | ((long)bytes[i] & 0xff) << (i*8)  ;
        }
        return l;
    }

}
