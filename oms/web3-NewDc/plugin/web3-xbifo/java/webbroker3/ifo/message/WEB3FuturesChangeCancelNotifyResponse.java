head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.22.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesChangeCancelNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP������t�X�V�C���^�Z�v�^(WEB3IfoAcceptedUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/22 Ḗ@@�� (���u) �V�K�쐬
*/


package webbroker3.ifo.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * (�����w���敨��������ʒm���X�|���X)<BR>
 * �����w���敨��������ʒm���X�|���X�N���X<BR>
 * @@author Ḗ@@��
 * @@version 1.0
 */
public class WEB3FuturesChangeCancelNotifyResponse extends WEB3BackResponse 
{
    public final static String PTYPE ="futures_changeCancelNotify";
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407221032L;  
    /**
     * @@roseuid 40F7AE1A02DE
     */
    public WEB3FuturesChangeCancelNotifyResponse() 
    {
     
    }
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�敨�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FuturesChangeCancelNotifyResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
}
@
