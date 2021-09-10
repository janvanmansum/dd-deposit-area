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
package nl.knaw.dans.depositarea;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import nl.knaw.dans.depositarea.core.DepositProperties;
import nl.knaw.dans.depositarea.db.DepositPropertiesDao;
import nl.knaw.dans.depositarea.resources.DepositPropertiesResource;

public class DdDepositAreaApplication extends Application<DdDepositAreaConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DdDepositAreaApplication().run(args);
    }

    private final HibernateBundle<DdDepositAreaConfiguration> hibernateBundle = new HibernateBundle<DdDepositAreaConfiguration>(DepositProperties.class) {

        @Override
        public DataSourceFactory getDataSourceFactory(DdDepositAreaConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public String getName() {
        return "DD Deposit Area";
    }

    @Override
    public void initialize(final Bootstrap<DdDepositAreaConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final DdDepositAreaConfiguration configuration, final Environment environment) {
        final DepositPropertiesDao dao = new DepositPropertiesDao(hibernateBundle.getSessionFactory());
        environment.jersey().register(new DepositPropertiesResource(dao));
    }

}
