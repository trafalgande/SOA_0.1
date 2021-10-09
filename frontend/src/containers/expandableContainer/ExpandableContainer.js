import {useState} from "react";
import {AiOutlineDown} from "@react-icons/all-files/ai/AiOutlineDown";
import './styles.css'

export const ExpandableContainer = (props) => {
    const {title} = props
    const [isOpen, setOpen] = useState(false)

    return (
        <div className={`accordion elevation-sm`}>
            <div className={`accordion-title`} onClick={e => setOpen(!isOpen)}>
                <span>{title}</span>
                <AiOutlineDown aria-expanded={isOpen} alt='Toggle Accordion' className='toggle'/>
            </div>
            <div className={`accordion-content`} aria-expanded={!isOpen}>
                {props.children}
            </div>
        </div>
    )
}