head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.56.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPContractForcedSettleResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ʋ������ό���(WEB3TPContractForcedSettleResult.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/25 �юu�� (���u) �V�K�쐬
Revesion History : 2008/01/29 �����F (���u) ���f��251
*/
package webbroker3.tradingpower;

import java.util.Date;

/**
 * ���ʋ������ό���
 * 
 * @@author �юu��(���u)
 * @@version 1.0
 */
public class WEB3TPContractForcedSettleResult
{
    /**
     * (����t���O)<BR>
     * <BR>
     * �������ϑΏیڋq�ł���ꍇ�Atrue���Z�b�g�A<BR>
     * �����łȂ��ꍇ�Afalse���Z�b�g<BR>
     */
    public boolean resultFlg;

    /**
     * (�������ϗ��R)<BR>
     *<BR>
     * [�ݒ�l]<BR>
     * �@@"1"�F�I�����C���J�n�O�i�d�x�j<BR>
     * �@@"2"�F�I�����C���J�n�O�i�y�x�j<BR>
     * �@@"3"�F���<BR>
     * �@@"4"�F�I�����C���J�n�O�i�@@��j <BR>
     * <BR>
     * ���I�����C���J�n�O�i�d�x�j�A�I�����C���J�n�O�i�y�x�j�A<BR>
     * �@@������̏����ɊY�������ꍇ�A���i�d�x�j���������ϗ��R�Ƃ���B<BR>
     * ������t���O==false�̏ꍇ�Anull���Z�b�g<BR>
     */
    public String forcedSettleReason;

    /**
     * (�Ǐؔ�����) <BR>
     * <BR>
     * ������t���O==false�̏ꍇ�Anull���Z�b�g<BR>
     */
    public Date additionalMarginDate;

    /**
     * (�Ǐؔ���������̌o�ߓ���) <BR>
     * <BR>
     * ������t���O==false�̏ꍇ�Anull���Z�b�g<BR>
     */
    public Integer additionalMarginAccruedDays;

    /**
     * (�ۏ؋��a����) <BR>
     * <BR>
     * ������t���O==false�̏ꍇ�Anull���Z�b�g<BR>
     */
    public Double marginMaintenanceRate;
}
@
