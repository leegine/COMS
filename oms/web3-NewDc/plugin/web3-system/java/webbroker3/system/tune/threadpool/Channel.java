head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.25.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	Channel.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �`���l���C���^�[�t�F�[�X(Channel.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/08/06 ��(FLJ) �V�K�쐬
 */
package webbroker3.system.tune.threadpool;

/*
 * �`���l���C���^�[�t�F�[�X
 */
public interface Channel
{

    /**
     * �`���l���ɃA�C�e��������
     **/
    public void put(Object item) throws InterruptedException;

    /**
     * ���Ԕ͈͓��A�`���l�����󂯓����\�A�C�e��������
     **/
    public boolean offer(Object item, long msecs) throws InterruptedException;

    /**
     * �`���l������A�C�e�������o����
     **/
    public Object take() throws InterruptedException;

    /**
     * ���Ԕ͈͓��A�`���l�������o�\�A�C�e�������o����
     **/
    public Object poll(long msecs) throws InterruptedException;

    /**
     * �`���l��������A�C�e�����擾����
     **/
    public Object peek();

}
@
