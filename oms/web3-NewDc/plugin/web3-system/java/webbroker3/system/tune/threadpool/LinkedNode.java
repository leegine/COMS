head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.25.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	LinkedNode.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �L���[�N���X(LinkedNode.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/08/06 ��(FLJ) �V�K�쐬
 */
package webbroker3.system.tune.threadpool;

/**
 * �L���[�̃m�[�g�N���X�iLinked Node�����j
 * **/
public class LinkedNode
{
    public Object value;
    public LinkedNode next;
    public LinkedNode()
    {}

    public LinkedNode(Object x)
    {
        value = x;
    }

    public LinkedNode(Object x, LinkedNode n)
    {
        value = x;
        next = n;
    }
}
@
