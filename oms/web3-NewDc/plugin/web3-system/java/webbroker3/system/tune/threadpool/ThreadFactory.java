head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.24.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	ThreadFactory.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ToString�ŏo�͂���镶����𐶐�����N���X(ThreadFactory.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/08/06 ��(FLJ) �V�K�쐬
 */
package webbroker3.system.tune.threadpool;

/*
 **
 * �X���b�h�쐬����t�@@�N�g���C���^�[�t�F�[�X.
 */
public interface ThreadFactory
{

    /**
     * �^����^�X�N�ɃX���b�h�Ń^�X�N�����s����
     **/
    public Thread newThread(Runnable command);

}
@
