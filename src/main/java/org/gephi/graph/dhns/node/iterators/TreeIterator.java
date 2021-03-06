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
import org.gephi.utils.collection.avl.ResetableIterator;

import java.util.Iterator;

/**
 * {@link AbstractNode} iterator for all nodes nodes. If predicate contains the **enabled** predicate,
 * skipping is true and descendants of enabled nodes are skipped
 *
 * @author Mathieu Bastian
 */
public class TreeIterator extends AbstractNodeIterator implements Iterator<Node>, ResetableIterator {

    protected int treeSize;
    protected DurableTreeList treeList;
    protected int nextIndex;
    protected int diffIndex;
    protected DurableAVLNode currentNode;
    //Settings
    protected final boolean skipping;
    //Predicate
    protected Predicate<AbstractNode> predicate;

    public TreeIterator(TreeStructure treeStructure, boolean skipping, Predicate<AbstractNode> predicate) {
        this.treeList = treeStructure.getTree();
        nextIndex = 1;
        diffIndex = 2;
        treeSize = treeList.size();
        this.skipping = skipping;
        this.predicate = predicate;
    }

    public void reset() {
        nextIndex = 1;
        diffIndex = 2;
    }

    public boolean hasNext() {
        while (true) {
            if (nextIndex < treeSize) {
                if (diffIndex > 1) {
                    currentNode = treeList.getNode(nextIndex);
                } else {
                    currentNode = currentNode.next();
                }

                if (skipping) {
                    while (!currentNode.getValue().isEnabled() || !predicate.evaluate(currentNode.getValue())) {
                        ++nextIndex;
                        if (nextIndex >= treeSize) {
                            return false;
                        }
                        currentNode = currentNode.next();
                    }
                    return true;
                } else {
                    while (!predicate.evaluate(currentNode.getValue())) {
                        ++nextIndex;
                        if (nextIndex >= treeSize) {
                            return false;
                        }
                        currentNode = currentNode.next();
                    }
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    public AbstractNode next() {
        if (skipping) {
            nextIndex = currentNode.getValue().getPre() + 1 + currentNode.getValue().size;
            diffIndex = nextIndex - currentNode.getValue().pre;
        } else {
            nextIndex++;
            diffIndex = 1;
        }
        return currentNode.getValue();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
