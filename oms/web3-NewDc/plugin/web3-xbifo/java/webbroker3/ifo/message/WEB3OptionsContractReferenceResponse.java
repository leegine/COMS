head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.14.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsContractReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�������ʏƉ�X�|���X(WEB3OptionsContractReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ���� (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (�����w���I�v�V�������ʏƉ�X�|���X)<BR>
 * �����w���I�v�V�������ʏƉ��ʕ\�����X�|���X�N���X<BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3OptionsContractReferenceResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "options_contractReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200406111525L;
    
    /**
     * (���ʏƉ��)
     */
    public WEB3OptionsContractReferenceUnit[] contractReferenceUnits;
    
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
     * �����w���I�v�V���������R�[�h����<BR>
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
    public WEB3OptionsContractReferenceResponse()
    {
        
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3OptionsContractReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
