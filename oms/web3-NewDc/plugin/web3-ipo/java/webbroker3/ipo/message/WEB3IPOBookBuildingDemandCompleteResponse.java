head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.39.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingDemandCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�u�b�N�r���f�B���O�\���������X�|���X�N���X(WEB3IPOBookBuildingDemandCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �A�C��(���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * IPO�u�b�N�r���f�B���O�\���������X�|���X�N���X
 * 
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3IPOBookBuildingDemandCompleteResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_bookBuildingDemandComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171830L;
    
    /**
     * �X�V����
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * ���ʔԍ�
     */
    public String orderActionId;
    
    /**
     * @@roseuid 4112EE2E0204
     */
    public WEB3IPOBookBuildingDemandCompleteResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40D16AA00017
     */
    public WEB3IPOBookBuildingDemandCompleteResponse(WEB3GenRequest l_request) 
    {
        super(l_request);     
    }
}
@
