import mpi.*;

import java.io.PrintStream;

public class Main {
    public static void main(String[] args) throws MPIException {
        MPI.Init(args);

        int myrank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        String message = "привет";
        char[] buffer = new char[message.length()];
        message.getChars(0, message.length(), buffer, 0);
        int TAG = 0;
        Status status;

        if (myrank % 2 == 0) {
            if (myrank + 1 != size) {
                MPI.COMM_WORLD.Send(buffer, 0, buffer.length, MPI.CHAR, myrank + 1, TAG);
                System.out.println(myrank + " отправил сообщение '" + message + "' получателю " + (myrank + 1));
            }
        } else {
            MPI.COMM_WORLD.Recv(buffer, 0, buffer.length, MPI.CHAR, myrank - 1, TAG);
            System.out.println(myrank + " получил: '" + message + "' от " + (myrank - 1));
        }

        MPI.Finalize();
    }
}