head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.43.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BizDateTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �c�Ɠ��敪 �萔��`�C���^�t�F�C�X(WEB3BizDateTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/09 ����@@���j(SRA) �V�K�쐬
Revesion History : 2007/12/18 �ЋŃV�� (���u) �y���ʁz�d�l�ύX�Ǘ��䒠No572(�c�a���C�A�E�g)��Ή�
*/
package webbroker3.common.define;

/**
 * �c�Ɠ��敪�@@�萔��`�C���^�t�F�C�X
 *
 * @@author ����@@���j(SRA)
 * @@version 1.0
 */
public interface WEB3BizDateTypeDef
{

    /**
     * 0 : ��c�Ɠ�
     */
    public static final String NO_BIZ_DATE = "0";

    /**
     * 1 : �I���c�Ɠ�
     */
    public static final String BIZ_DATE = "1";
    
    /**
     * 2 : ����(�ߑO��) 
     */
    public static final String BIZ_DATE_AM = "2";

    /**
     * 3 : ����(�ߌ���)
     */
    public static final String BIZ_DATE_PM = "3";
    
    /**
     * 4�F�I���c�Ɠ��i�ߑO�̂݁j
     */
    public static final String ALL_BIZ_DATE_AM = "4";
    
    /**
     * 5�F�I���c�Ɠ��i�ߌ�̂݁j
     */
    public static final String ALL_BIZ_DATE_PM = "5";
}
@
