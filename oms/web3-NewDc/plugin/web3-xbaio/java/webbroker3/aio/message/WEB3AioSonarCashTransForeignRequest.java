head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.02.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSonarCashTransForeignRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SONAR���o���i�O�݁j���N�G�X�g(WEB3AioSonarCashTransForeignRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 �Ԑi (���u) �V�K�쐬                  
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * (SONAR���o���i�O�݁j���N�G�X�g)<BR>
 * SONAR���o���i�O�݁j���N�G�X�g�N���X<BR>
 * 
 * @@author �Ԑi(���u)
 * @@version 1.0
 */
public class WEB3AioSonarCashTransForeignRequest extends WEB3AioSonarCashTransRequest 
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
     * @@roseuid 44EFFBD7029F
     */
    public WEB3AioSonarCashTransForeignRequest() 
    {
     
    }
    
    /**
     *�icreateResponse�̎����j<BR>
     * <BR>
     * SONAR���o���i�O�݁j���X�|���X�I�u�W�F�N�g��ԋp����B<BR>
     * @@return WEB3BackResponse
     * @@roseuid 4158E9B40347
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3AioSonarCashTransForeignResponse(this);
    }
}
@
