head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.38.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPODeclineCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO���ފ������X�|���X�N���X(WEB3IPODeclineCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �d�� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * IPO���ފ������X�|���X�N���X
 * 
 * @@author �d��
 * @@version 1.0
 */
public class WEB3IPODeclineCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_declineComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408100942L;
    
    /**
     * �X�V����
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * ���ʔԍ�
     */
    public String orderActionId;
    
    /**
     * @@roseuid 4112E44A0376
     */
    public WEB3IPODeclineCompleteResponse() 
    {
     
    }
    
    /**
     * ( IPO���ފ������X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40E11BAB032D
     */
    public WEB3IPODeclineCompleteResponse(WEB3GenRequest l_request) 
    {
        super(l_request); 
    }
}
@
