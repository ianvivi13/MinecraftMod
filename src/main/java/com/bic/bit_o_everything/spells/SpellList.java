package com.bic.bit_o_everything.spells;

import java.util.ArrayList;
import java.util.function.Supplier;

public class SpellList {

    public static final ArrayList<Supplier<? extends AbstractSpell>> SPELLS = new ArrayList<>(){{
        add(FireballSpell::new);
        add(SlowfallingSpell::new);
    }};

}
