head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.06.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MFStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����敪�萔��`�C���^�t�F�C�X(WEB3MFStatusDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/10 ��іQ(���u) �V�K�쐬
*/

package webbroker3.common.define;

/**
 * �����敪�萔��`�C���^�t�F�C�X<BR>
 * �i�莞��z���t�����ύX�e�[�u���̕ύX�敪�̎Q�l�p�j<BR>
 * <BR>
 * @@author ��іQ(���u)
 * @@version 1.0
 */
public interface WEB3MFStatusDef
{
    /**
     * 1:SONAR�����M
     */
    public final static String SONAR_NOT_SEND = "1";

    /**
     * 2:SONAR���M��
     */
    public final static String SONAR_SENDED = "2";

    /**
     * 3:SONAR���f��
     */
    public final static String SONAR_REFLECTED = "3";

    /**
     * 4:SONAR���M�ΏۊO
     */
    public final static String SONAR_SEND_EXCEPT_OBJECT = "4";
}@
