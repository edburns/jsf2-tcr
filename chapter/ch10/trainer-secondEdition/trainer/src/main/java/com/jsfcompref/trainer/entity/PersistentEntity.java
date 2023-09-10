

package com.jsfcompref.trainer.entity;

import java.io.Serializable;

/**
 *
 * @author Dr. Spock (spock at dev.java.net)
 */
public interface PersistentEntity<PK extends Serializable> extends Serializable {

    PK getId();

}
