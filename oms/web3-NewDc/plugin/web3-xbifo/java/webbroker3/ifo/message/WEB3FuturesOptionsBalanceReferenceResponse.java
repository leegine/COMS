head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.12.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsBalanceReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�I�v�V�����c���Ɖ�X�|���X�N���X(WEB3FuturesOptionsBalanceReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 ������ �V�K�쐬         
*/
package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�����w���敨�I�v�V�����c���Ɖ�X�|���X)<BR>
 * �����w���敨�I�v�V�����c���Ɖ�X�|���X�N���X<BR>
 * @@author ������
 * @@version 1.0  
 */
public class WEB3FuturesOptionsBalanceReferenceResponse extends WEB3GenResponse 
{
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 2004012291504L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futuresOptions_balanceReference";
    
    /**
     * (�c���Ɖ��)<BR>
     * �����w���敨�I�v�V�����c���Ɖ�ׂ̔z��<BR>
     */
    public WEB3FuturesOptionsDetailUnit[] balanceReference;
    
    /**
     * �����w���敨�I�v�V���������R�[�h����<BR>
     * (���������\���Ɏg�p)<BR>
     */
    public WEB3FuturesOptionsProductCodeNameUnit[] futOpProductCodeNames;
    
    /**
     * ���ۂɕ\������y�[�W�ʒu���w��<BR>
     * ���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex = "0";
    
    /**
     * ���y�[�W��<BR>
     */
    public String totalPages = "0";
    
    /**
     * �����R�[�h��<BR> 
     */
    public String totalRecords = "0";
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FuturesOptionsBalanceReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
