head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoAccEstablishSearchInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq���(WEB3AccInfoAccEstablishSearchInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �����q(���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq���)<BR>
 * �V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq���<BR>
 * @@author �����q
 * @@version 1.0
 */

public class WEB3AccInfoAccEstablishSearchInfo extends Message
{
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;

    /**
     * (���҃R�[�h)<BR>
     * ���҃R�[�h<BR>
     */
    public String traderCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;

    /**
     * (�������)<BR>
     * �������<BR>
     * <BR>
     * 1�F�@@�l�q<BR>
     * 2�F�@@�@@�l�q<BR>
     */
    public String accountTypeCode;

    /**
     * (�ڋq���i�����j)<BR>
     * �ڋq���i�����j<BR>
     */
    public String accountName;

    /**
     * (�ڋq���i�J�i))<BR>
     * �ڋq���i�J�i)<BR>
     */
    public String accountNameKana;

    /**
     * (�����J�ݓ�)<BR>
     * �����J�ݓ�<BR>
     */
    public Date accountOpenDate;

    /**
     * (�������s)<BR>
     * �������s<BR>
     */
    public String payFinancialInstitution;

    /**
     * (��s�x�X��)<BR>
     * ��s�x�X��<BR>
     */
    public String financialBranchName;

    /**
     * (�Ȗ�)<BR>
     * �Ȗ�<BR>
     */
    public String item;

    /**
     * (��s�ԍ�)<BR>
     * ��s�ԍ�<BR>
     */
    public String financialInstitutionNumber;

    /**
     * (��s�x�X�ԍ�)<BR>
     * ��s�x�X�ԍ�<BR>
     */
    public String financialBranchCode;

    /**
     * (��s�����ԍ�)<BR>
     * ��s�����ԍ�<BR>
     */
    public String financialAccountCode;

    /**
     * (�ڋq�Z���i�X�֔ԍ��j)<BR>
     * �ڋq�Z���i�X�֔ԍ��j<BR>
     */
    public String zipCode;

    /**
     * (�ڋq�Z���i�Z��1))<BR>
     * �ڋq�Z���i�Z��1�j<BR>
     */
    public String address1;

    /**
     * (�ڋq�Z���i�Z��2�j)<BR>
     * �ڋq�Z���i�Z��2�j<BR>
     */
    public String address2;

    /**
     * (�ڋq�Z���i�Z��3�j)<BR>
     * �ڋq�Z���i�Z��3�j<BR>
     */
    public String address3;

    /**
     * (���O�C�����b�N�t���O )<BR>
     * ���O�C�����b�N�t���O <BR>
     * <BR>
     * true�F�@@���O�C�����b�N��<BR>
     * false�F�@@���O�C�����b�N������<BR>
     */
    public boolean loginLockFlag;

    /**
     * (���O�C���G���[��)<BR>
     * ���O�C���G���[��<BR>
     */
    public String loginErrorCount;
}
@
