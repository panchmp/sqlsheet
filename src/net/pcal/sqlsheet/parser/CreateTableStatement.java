/*
 * Copyright 2008 pcal.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.pcal.sqlsheet.parser;

import java.util.List;

/**
 * Handle on a parsed SQL statement of the form
 * 
 * CREATE TABLE xxx (xxx xxx, xxx xxx)
 * 
 * @author <a href='http://www.pcal.net'>pcal</a>
 */
public interface CreateTableStatement extends ParsedStatement {

  public String getTable();

  public List<String> getColumns();

  public List<String> getTypes();

}
