head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.05.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSellCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M����񊮗����X�|���X�N���X(WEB3MutualSellCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11 ���� (���u) �V�K�쐬
                   2004/08/25 ���E (���u) ���r���[ 
                   2004/12/07 ������ (���u) �c�Ή�
*/
package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �����M����񊮗����X�|���X�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0  
 */

public class WEB3MutualSellCompleteResponse extends WEB3GenResponse {
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_sell_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408111710L;
    
    /**
     * �X�V����
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * ���ʔԍ�
     */
    public String orderActionId;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A89E3201A3
     */
    public WEB3MutualSellCompleteResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3MutualSellCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
