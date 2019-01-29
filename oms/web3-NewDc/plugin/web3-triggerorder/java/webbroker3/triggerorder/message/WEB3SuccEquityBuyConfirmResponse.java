head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.42.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccEquityBuyConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A���j�����������t�����m�F���X�|���X(WEB3SuccEquityBuyConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 �A�C��(���u) �V�K�쐬
                 : 2007/01/10 ���G��(���u) ���f��214
*/

package webbroker3.triggerorder.message;

import webbroker3.equity.message.WEB3EquityBuyConfirmResponse;


/**
 * (�i�A���j�����������t�����m�F���X�|���X)<BR>
 * �i�A���j�����������t�����m�F���X�|���X�B<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3SuccEquityBuyConfirmResponse extends WEB3EquityBuyConfirmResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200510111000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_equityBuyConfirm";

    /**
     * (������P��)<BR>
     * ������P���B<BR>
     * �}�w�l���w�肳�ꂽ�ꍇ�̂݃Z�b�g�B<BR>
     */
    public String afterAdjustmentPrice;

    /**
     * @@roseuid 43489606004E
     */
    public WEB3SuccEquityBuyConfirmResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3SuccEquityBuyConfirmResponse(WEB3SuccEquityBuyConfirmRequest l_request)
    {
        super(l_request);
    }     
}
@
