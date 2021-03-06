/*
Copyright 2008-2010 Gephi
Authors : Mathieu Bastian <mathieu.bastian@gephi.org>
Website : http://www.gephi.org

This file is part of Gephi.

DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.

Copyright 2011 Gephi Consortium. All rights reserved.

The contents of this file are subject to the terms of either the GNU
General Public License Version 3 only ("GPL") or the Common
Development and Distribution License("CDDL") (collectively, the
"License"). You may not use this file except in compliance with the
License. You can obtain a copy of the License at
http://gephi.org/about/legal/license-notice/
or /cddl-1.0.txt and /gpl-3.0.txt. See the License for the
specific language governing permissions and limitations under the
License.  When distributing the software, include this License Header
Notice in each file and include the License files at
/cddl-1.0.txt and /gpl-3.0.txt. If applicable, add the following below the
License Header, with the fields enclosed by brackets [] replaced by
your own identifying information:
"Portions Copyrighted [year] [name of copyright owner]"

If you wish your version of this file to be governed by only the CDDL
or only the GPL Version 3, indicate your decision by adding
"[Contributor] elects to include this software in this distribution
under the [CDDL or GPL Version 3] license." If you do not indicate a
single choice of license, a recipient has the option to distribute
your version of this file under either the CDDL, the GPL Version 3 or
to extend the choice of license to its licensees as provided above.
However, if you add GPL Version 3 code and therefore, elected the GPL
Version 3 license, then the option applies only if the new code is
made subject to such option by the copyright holder.

Contributor(s):

Portions Copyrighted 2011 Gephi Consortium.
*/
package org.gephi.graph.dhns.node.iterators;

import org.gephi.graph.api.Node;
import org.gephi.graph.dhns.core.DurableTreeList;
import org.gephi.graph.dhns.core.DurableTreeList.DurableAVLNode;
import org.gephi.graph.dhns.core.TreeStructure;
import org.gephi.graph.dhns.node.AbstractNode;
import org.gephi.graph.dhns.predicate.Predicate;

import java.util.Iterator;

/**
 * Iterates over nodes for a give level.
 *
 * @author Mathieu Bastian
 */
public class LevelIterator extends AbstractNodeIterator implements Iterator<Node> {

    protected DurableTreeList treeList;
    protected int nextIndex;
    protected int diffIndex;
    protected int treeSize;
    protected DurableAVLNode currentNode;
    protected int level;

    //Proposition
    protected Predicate<AbstractNode> predicate;

    public LevelIterator(TreeStructure treeStructure, int level, Predicate<AbstractNode> predicate) {
        this.treeList = treeStructure.getTree();
        this.nextIndex = 1;
        this.diffIndex = 2;
        this.treeSize = treeList.size();
        this.level = level;
        this.predicate = predicate;
    }

    @Override
    public boolean hasNext() {
        while (true) {
            if (nextIndex < treeSize) {
                if (diffIndex > 1) {
                    currentNode = treeList.getNode(nextIndex);
                } else {
                    currentNode = currentNode.next();
                }

                while (currentNode.getValue().level != level) {
                    ++nextIndex;
                    if (nextIndex >= treeSize) {
                        return false;
                    }
                    currentNode = currentNode.next();
                }

                if (!predicate.evaluate(currentNode.getValue())) {
                    nextIndex = currentNode.getValue().getPre() + 1 + currentNode.getValue().size;
                    diffIndex = nextIndex - currentNode.getValue().pre;
                } else {
                    return true;
                }

            } else {
                return false;
            }
        }
    }

    @Override
    public AbstractNode next() {
        nextIndex = currentNode.getValue().getPre() + 1 + currentNode.getValue().size;
        diffIndex = nextIndex - currentNode.getValue().pre;
        return currentNode.getValue();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
