head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.17.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConTransferReportUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���U�փ��|�[�g����(WEB3FEqConTransferReportUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ���E(���u) �V�K�쐬
                   2006/02/08 ����(���u) �d�l�ύX�E���f��481
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;
import java.util.Date;

/**
 * (�O���U�փ��|�[�g����)<BR>
 * �O���U�փ��|�[�g���׃N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3FEqConTransferReportUnit extends Message 
{
    
    /**
     * (�U�֋敪)<BR>
     * �U�֋敪<BR>
     * <BR>
     * 1�F����<BR>
     * 2�F�o��
     */
    public String transferDiv;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     */
    public String accountCode;
    
    /**
     * (���O�i���j)<BR>
     * �ڋq��(��)
     */
    public String familyName;
    
    /**
     * (���O�i���j)<BR>
     * �ڋq��(��)
     */
    public String name;
    
    /**
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h
     */
    public String requestNumber;
    
    /**
     * (��t����)<BR>
     * ��t����
     */
    public Date receptionDate;
    
    /**
     * (�U�֓�)<BR>
     * �U�֓�
     */
    public String transferDate;
    
    /**
     * (�U�֋��z)<BR>
     * �U�֋��z
     */
    public String changeAmt;
    
    /**
     * (�O�������ԍ�)<BR>
     * �O�������ԍ�
     */
    public String fstkAccountCode;
    
    /**
     * (UWG��t����)<BR>
     * UWG��t����
     */
    public Date uwgReceptionDate;
    
    /**
     * (�X�e�[�^�X�敪)<BR>
     * 0�F���ϒ�<BR>
     * 1�F���ϊ���<BR>
     * 2�F���σG���[<BR>
     * 3�F���
     */
    public String statusDiv;
    
    /**
     * (���b�Z�[�W)<BR>
     * 10000000�F��t�� <BR> 
     * 20000000�F���ϒ� <BR> 
     * 90000000�F����� <BR> 
     * 99999999�F���ώ��s�i�V�X�e���G���[�j <BR> 
     * 00000000�F���ϊ���<BR> 
     * 90000009:��������
     */
    public String message;
    
    /**
     * (�O���U�փ��|�[�g����)<BR>
     * �R���X�g���N�^
     * @@roseuid 41D0BA6200ED
     */
    public WEB3FEqConTransferReportUnit() 
    {
     
    }
}
@
