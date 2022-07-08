package util;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileUtil <T>{
    private final String fileName;

    public FileUtil(String fileName) {
        this.fileName = fileName;
    }

    public Optional<List<T>> readFile(Function<String, T> mapper){
        Path filePath = Paths.get(System.getProperty("user.dir"),"\\src\\main\\resources", this.fileName);

        if(Files.exists(filePath)){
            try{
                var file = Files.lines(filePath)
                        .skip(1)
                        .map(mapper)
                        .collect(Collectors.toList());
                return Optional.of(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Arquivo não encontrado!");
        return Optional.empty();
    }
}
