head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.42.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccEquitySellCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�����������t�����������X�|���X(WEB3SuccEquitySellCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 �A�C��(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import webbroker3.equity.message.WEB3EquitySellCompleteResponse;


/**
 * (�i�A���j�����������t�����������X�|���X)<BR>
 * �i�A���j�����������t�����������X�|���X�B
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3SuccEquitySellCompleteResponse extends WEB3EquitySellCompleteResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200510111000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_equitySellComplete";
    
    /**
     * @@roseuid 4348960502AF
     */
    public WEB3SuccEquitySellCompleteResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3SuccEquitySellCompleteResponse(WEB3SuccEquitySellCompleteRequest l_request)
    {
        super(l_request);
    }     
}
@
