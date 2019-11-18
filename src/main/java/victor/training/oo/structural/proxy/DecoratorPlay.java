package victor.training.oo.structural.proxy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class DecoratorPlay {
    public static void main(String[] args) throws IOException {

        Writer writer = new FileWriter("a.txt");

        writer = new BufferedWriter(writer);

        writer.write("Something");

    }
}
