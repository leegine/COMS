head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.16.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����ԍψꗗ��ʕ\�����X�|���X�N���X(WEB3OptionsCloseMarginListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ������ �V�K�쐬
              001: 2004/08/04 ���Ō� �Ή��o�b�O U00016
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (�����w���I�v�V�����ԍψꗗ��ʕ\�����X�|���X)<BR>
 * �����w���I�v�V�����ԍψꗗ��ʕ\�����X�|���X�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3OptionsCloseMarginListResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "options_closeMarginList";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406101442L;
        
    /**
     * �����w���I�v�V�����ԍψꗗ�s
     */
    public WEB3OptionsCloseMarginGroup[] closeMarginGroups;
    
    /**
     * ���ۂɕ\������y�[�W�ʒu���w��@@���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;
    
    /**
     * ���y�[�W��<BR>
     */
    public String totalPages;
    
    /**
     * �����R�[�h��<BR>
     */
    public String totalRecords;
    
    /**
     * �Ǌԋ߂̎s�ꂪ����΁A���̃R�[�h���i�[<BR>
     */
    public String[] messageSuspension;
    
    /**
     * �����w���敨�I�v�V���������R�[�h����<BR>
     * (���������\���Ɏg�p)<BR>
     */
    //Start 2004/08/04 ���Ō� �Ή��o�b�O U00016
    //public WEB3FuturesOptionsProductCodeNameUnit[] opProductCodeNames;
    public WEB3FuturesOptionsProductCodeNameUnit[] futOpProductCodeNames;
    //End 2004/08/04 ���Ō� �Ή��o�b�O U00016
    
    /**
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3OptionsCloseMarginListResponse()
    {
        
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3OptionsCloseMarginListResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
