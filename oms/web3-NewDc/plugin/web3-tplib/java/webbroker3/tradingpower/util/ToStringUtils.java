head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.36.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	ToStringUtils.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Object#toString()���\�b�h���������邽�߂̃��[�e�B���e�B��񋟂���N���X(ToStringUtils.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/08/03 �R�c�@@��i(FLJ) �V�K�쐬
*/
package webbroker3.tradingpower.util;

/**
 * Object#toString()���\�b�h���������邽�߂̃��[�e�B���e�B��񋟂���N���X<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public final class ToStringUtils
{
    
    /**
     * �]�͌v�Z���W���[���Ŏg�p����ToStringStyle�N���X
     */
    private static final ToStringStyle WEB3TP_STYLE = new ToStringStyle();

    /**
     * �V����ToStringBuilder�̃C���X�^���X�𐶐�����B
     * 
     * @@param object toString���\�b�h����������ΏۂƂȂ�I�u�W�F�N�g
     * @@return ToStringBuilder�̃C���X�^���X
     */
    public static ToStringBuilder newToStringBuilder(Object object)
    {
        return new ToStringBuilder(object, WEB3TP_STYLE, new StringBuffer());
    }
    
}
@
