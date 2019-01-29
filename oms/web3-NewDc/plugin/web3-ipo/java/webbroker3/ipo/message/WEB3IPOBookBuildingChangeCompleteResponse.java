head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.35.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�u�b�N�r���f�B���O�����������X�|���X�N���X(WEB3IPOBookBuildingChangeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 ���]��(���u) �V�K�쐬
*/


package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * IPO�u�b�N�r���f�B���O�����������X�|���X�N���X
 * @@author ���]��(���u)
 * @@version 1.0
 */
public class WEB3IPOBookBuildingChangeCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_bookBuildingChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171435L;
    
    /**
     * �X�V����
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * ���ʔԍ�
     */
    public String orderActionId;
    
    /**
     * @@roseuid 4112EDC40143
     */
    public WEB3IPOBookBuildingChangeCompleteResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40D16DF30140
     */
    public WEB3IPOBookBuildingChangeCompleteResponse(WEB3GenRequest l_request) 
    {
        super(l_request); 
    }
}
@
