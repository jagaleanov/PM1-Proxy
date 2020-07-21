package proxy;

public interface SocketInter {
	String readLine();
    void  writeLine(String str);
    void  dispose();
}
