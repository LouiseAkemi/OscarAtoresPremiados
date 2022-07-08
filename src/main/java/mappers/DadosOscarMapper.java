package mappers;
import model.AtorGanhadorOscar;
import java.util.function.Function;
import static java.lang.Integer.parseInt;

public class DadosOscarMapper implements Function<String, AtorGanhadorOscar> {
    @Override
    public AtorGanhadorOscar apply(String l) {
        String[] dadosLinha = l.split(";");
        int ano = parseInt(dadosLinha[1].trim());
        int idade = parseInt(dadosLinha[2].trim());
        String nome = dadosLinha[3].trim();
        String filme = dadosLinha[4].trim();

        return new AtorGanhadorOscar(nome, idade, ano, filme);
    }
}
