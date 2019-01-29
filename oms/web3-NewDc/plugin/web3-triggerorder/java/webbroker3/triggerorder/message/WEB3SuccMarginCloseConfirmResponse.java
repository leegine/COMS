head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.49.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccMarginCloseConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p����ԍϒ����m�F���X�|���X�N���X(WEB3SuccMarginCloseConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 �A����(���u) �V�K�쐬
                 : 2007/01/10 ���G��(���u) ���f��214
*/

package webbroker3.triggerorder.message;

import webbroker3.equity.message.WEB3MarginCloseMarginConfirmResponse;

/**
 * (�i�A���j�M�p����ԍϒ����m�F���X�|���X�N���X)<BR>
 * �i�A���j�M�p����ԍϒ����m�F���X�|���X�N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0 
 */
public class WEB3SuccMarginCloseConfirmResponse extends WEB3MarginCloseMarginConfirmResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_marginCloseConfirm";

    /**
     * (������P��)<BR>
     * ������P���B<BR>
     * �}�w�l���w�肳�ꂽ�ꍇ�̂݃Z�b�g�B<BR>
     */
    public String afterAdjustmentPrice;

    /**
     * @@roseuid 4369CC0F002E
     */
    public WEB3SuccMarginCloseConfirmResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3SuccMarginCloseConfirmResponse(WEB3SuccMarginCloseConfirmRequest l_request)
    {
        super(l_request);
    }     
}
@