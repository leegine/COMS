head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.23.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptPasswordCheckUtil.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name           : �ڋq�p�X���[�h�`�F�b�N�T�[�r�XUTIL(WEB3AcceptPasswordCheckUtil.java)
 Author Name         : Daiwa Institute of Research
 Revesion History    : 2005/10/28 �V���@@�h�O(FLJ) �V�K�쐬
 */

package webbroker3.login.service.delegate.stdimpls;

import webbroker3.common.*;

public class WEB3AcceptPasswordCheckUtil
{
    public WEB3Crypt web3Crypt = new WEB3Crypt();

    private static WEB3AcceptPasswordCheckUtil _instance = new
        WEB3AcceptPasswordCheckUtil();

    private WEB3AcceptPasswordCheckUtil()
    {
    }

    public static WEB3AcceptPasswordCheckUtil getInstance()
    {
        return _instance;
    }

    /**
     * ������̈Í������s���B
     * �������Í�������������̏ꍇ�́A���̕���������̂܂ܕԂ��B
     *
     * @@param l_strPlane �Í���������������
     * @@return �Í�������������
     */
    public synchronized String encrypt(String l_strPlane)
    {
        return web3Crypt.encrypt(l_strPlane);
    }

}
@
