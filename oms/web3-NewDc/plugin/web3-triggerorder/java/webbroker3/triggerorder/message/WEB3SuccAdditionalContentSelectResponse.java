head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.40.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccAdditionalContentSelectResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ǉ����e�I�����X�|���X(WEB3SuccAdditionalContentSelectResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 ������(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�ǉ����e�I�����X�|���X)<BR>
 * �ǉ����e�I�����X�|���X�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3SuccAdditionalContentSelectResponse extends WEB3GenResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200510111000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_additionalContentSelect";
    
    /**
     * (�i�e�����j����ID)<BR>
     * �i�e�����j����ID�B<BR>
     */
    public String parentOrderId;
    
    /**
     * (�A����������ꗗ)<BR>
     * �A������������̔z��<BR>
     */
    public WEB3SuccTradingInfo[] succTradingList;
    
    /**
     * @@roseuid 43489604034B
     */
    public WEB3SuccAdditionalContentSelectResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3SuccAdditionalContentSelectResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }     
}
@
