head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ComplianceInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �R���v���C�A���X���N���X(WEB3ComplianceInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/23 �h�C (���u) �V�K�쐬
Revesion History : 2006/10/25 �h�C (���u) �d�l�ύX ���f��214��Ή�
Revesion History : 2007/01/19 �h�C (���u) �d�l�ύX ���f��220��Ή�
*/
package webbroker3.common.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�R���v���C�A���X���)<BR>
 * �R���v���C�A���X���N���X<BR>
 * <BR>
 * @@author  �h�C (���u)
 * @@version 1.0
 */
public class WEB3ComplianceInfoUnit extends Message
{
    /**
     * (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     */
    public String institutionCode;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;

    /**
     * (�T�[�r�XID)<BR>
     * �T�[�r�XID<BR>
     * <BR>
     * 1�F�����t���� <BR>
     * 2�F�����p  <BR>
     * 3�F����� <BR>
     * 4�F�����J�� <BR>
     * 5�F�ڋq���b�N�@@ <BR>
     * 6�F�ڋq���� <BR>
     */
    public String serviceId;

    /**
     * (AuditID)<BR>
     * AuditID<BR>
     */
    public String auditId;

    /**
     * (�g�����U�N�V����ID)<BR>
     * �g�����U�N�V����ID(�O���V�X�e���A�g�p�j<BR>
     */
    public String transactionId;

    /**
     * (����No)<BR>
     * ����No<BR>
     */
    public String orderNo;

    /**
     * (����敪)<BR>
     * ����敪<BR>
     * <BR>
     * 1:�R���f�[�^���M <BR>
     * 2:���F�\�� <BR>
     * 3:�۔F�\�� <BR>
     * 4:��� <BR>
     * 5:���F <BR>
     * 6:�۔F <BR>
     */
    public String controlDiv;

    /**
     * (�o�H)<BR>
     * �o�H<BR>
     * <BR>
     * �P�FWEB3���� <BR>
     * �Q�F�O���V�X�e��<BR>
     */
    public String routeDiv;

    /**
     * (�E�v�R�[�h)<BR>
     * �E�v�R�[�h<BR>
     */
    public String remarksCode;

    /**
     * (�E�v)<BR>
     * �E�v<BR>
     */
    public String remarks;

    /**
     * (�E�v�R�[�h�ꗗ)<BR>
     * �E�v�R�[�h�ꗗ<BR>
     */
    public String[] remarksList;

    /**
     * (�������)<BR>
     * �������<BR>
     * <BR>
     * �P�F���F�\����<BR>
     * �Q�F����� <BR>
     * �R�F���F�� <BR>
     * �S�F�۔F�� <BR>
     * �T�F�۔F�\���� <BR>
     * �U�F���\��<BR>
     */
    public String orderStatus;

    /**
     * (���̓��N�G�X�g)<BR>
     * ���̓��N�G�X�g<BR>
     */
    public WEB3GenRequest inputRequest;

    /**
     * (���̓��X�|���X)<BR>
     * ���̓��X�|���X<BR>
     */
    public WEB3GenResponse inputResponse;

    /**
     * (�m�F���N�G�X�g)<BR>
     * �m�F���N�G�X�g<BR>
     */
    public WEB3GenRequest confirmRequest;

    /**
     * (�m�F���X�|���X)<BR>
     * �m�F���X�|���X<BR>
     */
    public WEB3GenResponse confirmResponse;
}
@
