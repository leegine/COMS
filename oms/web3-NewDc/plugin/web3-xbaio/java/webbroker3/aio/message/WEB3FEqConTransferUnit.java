head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.58.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConTransferUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���U�֖���(WEB3FEqConTransferUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/21 ���E(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;
import java.util.Date;

/**
 * (�O���U�֖���)<BR>
 * �O���U�֖��׃N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3FEqConTransferUnit extends Message 
{
    
    /**
     * (����ID)<BR>
     * ����ID
     */
    public String orderId;
    
    /**
     * (��t����)<BR>
     * ��t����
     */
    public Date receptionDate;
    
    /**
     * (�U�֋��z)<BR>
     * �U�֋��z
     */
    public String changeAmt;
    
    /**
     * (��n�\���)<BR>
     * ��n�\���
     */
    public Date deliveryScheduledDate;
    
    /**
     * (������)<BR>
     * ������<BR>
     * <BR>
     * 0�F �U�֒�<BR>
     * 1�F UWG���ϒ�<BR>
     * 2�F UWG���ϊ���<BR>
     * 3�F UWG���σG���[<BR>
     * 4�F �����
     */
    public String transactionStateType;
    
    /**
     * (����\�t���O)<BR>
     * ����\�t���O<BR>
     * <BR>
     * ����\�F true<BR>
     * ����s�F false
     */
    public boolean cancelFlag;
    
    /**
     * (�O���U�֖���)<BR>
     * �R���X�g���N�^
     * @@roseuid 41CBF4D60343
     */
    public WEB3FEqConTransferUnit() 
    {
     
    }
}
@
