head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.58.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSonarCashTransForeignResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SONAR���o���i�O�݁j���X�|���X(WEB3AioSonarCashTransForeignResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 �Ԑi (���u) �V�K�쐬               
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;


/**
 * (SONAR���o���i�O�݁j���X�|���X)<BR>
 * SONAR���o���i�O�݁j���X�|���X�N���X<BR>
 * 
 * @@author �Ԑi(���u)
 * @@version 1.0
 */
public class WEB3AioSonarCashTransForeignResponse extends WEB3AioSonarCashTransResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_sonar_cash_trans_foreign";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608281448L;
    
    
    /**
     * @@roseuid 44EFFBD70251
     */
    public WEB3AioSonarCashTransForeignResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AioSonarCashTransForeignResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }  
}
@
