head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.46.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccEquityChangeConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�����������������m�F���X�|���X(WEB3SuccEquityChangeConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/7  鰊](���u) �V�K�쐬
                 : 2007/01/10 ���G��(���u) ���f��214
*/

package webbroker3.triggerorder.message;

import webbroker3.equity.message.WEB3EquityChangeConfirmResponse;


/**
 * (�i�A���j�����������������m�F���X�|���X)<BR>
 * �i�A���j�����������������m�F���X�|���X�B<BR>
 * 
 * @@ author 鰊] <BR>
 * @@ version 1.0 <BR>
 */
public class WEB3SuccEquityChangeConfirmResponse extends WEB3EquityChangeConfirmResponse 
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_equityChangeConfirm";

    /**
     * (������P��)<BR>
     * ������P���B<BR>
     * �}�w�l���w�肳�ꂽ�ꍇ�̂݃Z�b�g�B<BR>
     */

    public String afterAdjustmentPrice;
    /**
     * @@roseuid 4369CC830186
     */
    public WEB3SuccEquityChangeConfirmResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3SuccEquityChangeConfirmResponse(WEB3SuccEquityChangeConfirmRequest l_request)
    {
        super(l_request);
    }     
}
@
