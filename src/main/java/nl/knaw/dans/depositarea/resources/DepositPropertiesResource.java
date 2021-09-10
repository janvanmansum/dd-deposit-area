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
package nl.knaw.dans.depositarea.resources;

import io.dropwizard.hibernate.UnitOfWork;
import nl.knaw.dans.depositarea.core.DepositProperties;
import nl.knaw.dans.depositarea.db.DepositPropertiesDao;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/deposits")
@Produces(MediaType.APPLICATION_JSON)
public class DepositPropertiesResource {

    private DepositPropertiesDao dao;

    public DepositPropertiesResource(DepositPropertiesDao dao) {
        this.dao = dao;
    }

    @POST
    @UnitOfWork
    public void add(DepositProperties properties) {
        dao.save(properties);
    }

    @GET
    @Path("/{uuid}")
    @UnitOfWork
    public DepositProperties get(@PathParam("uuid") String uuid) {
        return dao.findById(uuid).orElseThrow(() -> new NotFoundException("No such deposit"));
    }

}
