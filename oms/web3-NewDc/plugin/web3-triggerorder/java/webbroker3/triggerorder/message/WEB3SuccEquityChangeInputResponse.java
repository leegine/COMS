head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.43.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccEquityChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�������������������̓��X�|���X(WEB3SuccEquityChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/7  鰊](���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import webbroker3.equity.message.WEB3EquityChangeInputResponse;


/**
 * (�i�A���j�������������������̓��X�|���X)<BR>
 * �i�A���j�������������������̓��X�|���X�B<BR>
 * 
 * @@ author 鰊] <BR>
 * @@ version 1.0 <BR>
 */
public class WEB3SuccEquityChangeInputResponse extends WEB3EquityChangeInputResponse 
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_equityChangeInput";
    
    /**
     * (�A���������ʏ��)<BR>
     * �A���������ʏ��B<BR>
     */
    public WEB3SuccCommonInfo succCommonInfo;
    
    /**
     * (�P�������l���)<BR>
     * �P�������l���B<BR>
     * �����Ώے����Ɂ}�w�l���w�肳��Ă���ꍇ�̂݃Z�b�g�B<BR>
     */
    public WEB3SuccPriceAdjustmentValueInfo priceAdjustmentValueInfo;
    
    /**
     * (���������敪�ꗗ)<BR>
     * ���������敪�ꗗ�B<BR>
     * �i1�F��������@@2�F�o����܂Œ����j<BR>
     */
    public String[] expirationDateTypeList;
    
    /**
     * @@roseuid 4369CC8400CB
     */
    public WEB3SuccEquityChangeInputResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3SuccEquityChangeInputResponse(WEB3SuccEquityChangeInputRequest l_request)
    {
        super(l_request);
    }     
}
@
