package utils;

import common.Customer;
import common.Server;
import server.ServerImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIUtils {

    public static Server connect(String host, int port, String name) {
        System.setProperty("java.rmi.server.hostname", "localhost");
        try {
            return (Server) LocateRegistry
                    .getRegistry(host, port)
                    .lookup(name);
        } catch (RemoteException | NotBoundException e) {
            System.err.println("서버 연결에 실패했습니다.\n" +
                    "나중에 다시 시도해주세요.\n" +
                    "불편을 드려 죄송합니다.");
            return null;
        }
    }

    public static boolean initRegistry(int port, String name) {
        System.setProperty("java.rmi.server.hostname", "localhost");
        try {
            LocateRegistry.createRegistry(port).bind(name, new ServerImpl());
            System.out.println("레지스트리 등록에 성공했습니다.");
            return true;
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
            System.out.println("레지스트리 등록에 실패했습니다.");
            return false;
        }
    }

}
