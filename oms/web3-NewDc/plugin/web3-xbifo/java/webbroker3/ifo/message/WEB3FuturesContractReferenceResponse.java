head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.18.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesContractReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨���ʏƉ�X�|���X(WEB3FuturesContractReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 ���Q (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (�����w���敨���ʏƉ�X�|���X)<BR>
 * �����w���敨���ʏƉ��ʕ\�����X�|���X�N���X<BR>
 *                                                                     
 * @@author ���Q
 * @@version 1.0
 */
public class WEB3FuturesContractReferenceResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_contractReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200407201058L;
    
    /**
     * (���ʏƉ��)
     */
    public WEB3FuturesContractReferenceUnit[] contractReferenceUnits;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * ���ۂɕ\������y�[�W�ʒu���w��<BR>
     * ���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;
    
    /**
     * (���y�[�W��)
     */
    public String totalPages;
    
    /**
     * (�����R�[�h��)
     */
    public String totalRecords;
    
    /**
     * �����w���敨�����R�[�h����<BR>
     * (���������\���Ɏg�p)<BR>
     */
    public WEB3FuturesOptionsProductCodeNameUnit[] futOpProductCodeNames;
    
    /**
     * (�ڋq���b�N�敪)<BR>
     * true�F���b�N�ڋq<BR>
     * false�F���b�N�ڋq�ł͂Ȃ�<BR>
     */
    public boolean accountLock;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3FuturesContractReferenceResponse()
    {
        
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FuturesContractReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
