head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.23.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����ԍϊ������X�|���X�N���X(WEB3OptionsCloseMarginCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ������ �V�K�쐬
              001: 2004/07/30 ���Ō� (���u) �Ή��o�b�O WEB3_IFO_UT-000088
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;



/**
 * (�����w���I�v�V�����ԍϊ������X�|���X)<BR>
 * �����w���I�v�V�����ԍϊ������X�|���X�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3OptionsCloseMarginCompleteResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    //Start 2004/07/30 ���Ō� (���u) �Ή��o�b�O WEB3_IFO_UT-000088
    //public final static  String PTYPE = "options_closeMarginComple";
    public final static  String PTYPE = "options_closeMarginComplete";
    //End 2004/07/30 ���Ō� (���u) �Ή��o�b�O WEB3_IFO_UT-000088
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406111930L;
        
    /**
     * �X�V����
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (���ʔԍ�)<BR>
     * ���������h�c<BR>
     */
    public String orderActionId;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3OptionsCloseMarginCompleteResponse()
    {
        
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3OptionsCloseMarginCompleteResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
