package ma.cigma.domaine;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Order {

    private Pizza pizza;
    private Dip[] dips;

}