import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException ;
import java.io.LineNumberReader;
import java.nio.file.FileVisitResult ;
import java.nio.file.Files ;
import java.nio.file.Path ;
import java.nio.file.Paths ;
import java.nio.file.SimpleFileVisitor ;
import java.nio.file.attribute.BasicFileAttributes ;

public class ContadorDeLinhas {

    public static void main(String[] args) {
        int qtdLinha = 0;
        int totalLinhas = 0;
            Path startPath = Paths.get("/home/jpcabral/");
            Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws FileNotFoundException, IOException {
                    if (file.toString().endsWith(".java")){
                        LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(file.toString()));
                        linhaLeitura.skip(file.toString().length());
                        int qtdLinha = linhaLeitura.getLineNumber();
                        int totalLinhas =+ qtdLinha;
                        System.out.println("Total de linhas de todos os arquivos: " + totalLinhas);
                        System.out.println("Total de linhas: " + qtdLinha + " Arquivo: " + file.toString());
                    }
                    return FileVisitResult.CONTINUE;
                }
                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    return FileVisitResult.CONTINUE;
                }
            });
    }

}
