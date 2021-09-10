/**
 * Copyright (C) 2021 DANS - Data Archiving and Networked Services (info@dans.knaw.nl)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.knaw.dans.depositarea.db;

import io.dropwizard.hibernate.AbstractDAO;
import nl.knaw.dans.depositarea.core.DepositProperties;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class DepositPropertiesDao extends AbstractDAO<DepositProperties> {

    public DepositPropertiesDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public DepositProperties save(DepositProperties p) {
        return persist(p);
    }

    public Optional<DepositProperties> findById(String id) {
        return Optional.ofNullable(get(id));
    }
}
