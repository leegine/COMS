head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.49.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������������X�|���X(WEB3BondCancelCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 ���� (���u) �V�K�쐬
*/
package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (������������X�|���X)<BR>
 * ������������X�|���X
 * 
 * @@author ����
 * @@version 1.0
 */
public class WEB3BondCancelCompleteResponse extends WEB3GenResponse
{
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_cancel_complete";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609201906L;  
    
    /**
     * (�X�V����)<BR>
     * �X�V����<BR>
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (������������X�|���X)<BR>
     * �R���X�g���N�^<BR> 
     */
    public WEB3BondCancelCompleteResponse()
    {
        
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3BondCancelCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
